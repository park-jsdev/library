# Load necessary libraries
library(tidyverse)
library(readxl)
library(dplyr)
library(circlize)
library(ggplot2)
library(png)
library(grid)

# Read the dataset from the specified sheet
data <- read_excel("data.xlsx", sheet = "Disease vs AI Task")

# Clean the data by dropping rows with NA in key columns and removing extra spaces
data_clean <- data %>%
  mutate(across(everything(), ~str_trim(.))) %>%
  drop_na(`AI/ML Task Area`, `Disease model`)

# Create a contingency table
contingency_table <- table(data_clean$`Disease model`, data_clean$`AI/ML Task Area`)

# Ensure all disease models and AI/ML task areas are included, even if they have no connections
all_disease_models <- unique(data_clean$`Disease model`)
all_task_areas <- unique(data_clean$`AI/ML Task Area`)
contingency_table <- contingency_table[match(all_disease_models, rownames(contingency_table)), 
                                       match(all_task_areas, colnames(contingency_table))]
contingency_table[is.na(contingency_table)] <- 0

# Convert the table to a dataframe
df_for_chord <- as.data.frame(as.table(contingency_table))
names(df_for_chord) <- c("DiseaseModel", "TaskArea", "Count")

# Ensure the factors are in the correct order
df_for_chord$DiseaseModel <- factor(df_for_chord$DiseaseModel, levels = all_disease_models)
df_for_chord$TaskArea <- factor(df_for_chord$TaskArea, levels = all_task_areas)

# Prepare the data for the chord diagram
# Create a matrix from the dataframe
chord_matrix <- xtabs(Count ~ DiseaseModel + TaskArea, data = df_for_chord)

# Define a custom color palette inspired by Tailwind CSS
tailwind_colors <- c(
  "#ef4444", "#f97316", "#f59e0b", "#eab308", "#84cc16", "#22c55e", "#10b981", "#14b8a6", 
  "#06b6d4", "#0ea5e9", "#3b82f6", "#6366f1", "#8b5cf6", "#a855f7", "#d946ef", "#ec4899", 
  "#f43f5e"
)

# Ensure the color palette is long enough
if (length(tailwind_colors) < length(unique(c(df_for_chord$DiseaseModel, df_for_chord$TaskArea)))) {
  tailwind_colors <- rep(tailwind_colors, length.out = length(unique(c(df_for_chord$DiseaseModel, df_for_chord$TaskArea))))
}

# Create a named vector of colors
category_colors <- tailwind_colors[1:length(unique(c(df_for_chord$DiseaseModel, df_for_chord$TaskArea)))]
names(category_colors) <- c(levels(df_for_chord$DiseaseModel), levels(df_for_chord$TaskArea))

# Create the chord diagram and save it as a PNG file
png("chord_diagram_tw.png", width = 1600, height = 1600, bg = "white")  # Ensure background is white and larger size
circos.clear()
circos.par(
  gap.after = c(rep(5, nrow(chord_matrix) - 1), 15, rep(5, ncol(chord_matrix) - 1), 15),
  start.degree = 90,
  track.margin = c(0.01, 0.01),  # Reduce the margin between tracks
  canvas.xlim = c(-1.2, 1.2),  # Expand the canvas limits
  canvas.ylim = c(-1.2, 1.2)   # Expand the canvas limits
)
chordDiagram(
  chord_matrix,
  transparency = 0.5,
  annotationTrack = "grid",
  preAllocateTracks = list(track.height = 0.1),
  grid.col = category_colors  # Use the defined colors for the grid
)
circos.track(
  track.index = 1,
  panel.fun = function(x, y) {
    sector.name = get.cell.meta.data("sector.index")
    circos.text(CELL_META$xcenter, CELL_META$ylim[1], sector.name, 
                facing = "clockwise", niceFacing = TRUE, adj = c(0, 0.5), cex = 1.2)  # Increase text size
  },
  bg.border = NA
)
dev.off()

# Sum the counts for each disease model and task area for sorting
disease_counts <- df_for_chord %>%
  group_by(DiseaseModel) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(TotalCount)

task_counts <- df_for_chord %>%
  group_by(TaskArea) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(TotalCount)

# Reorder the data frames for plotting
df_for_chord$DiseaseModel <- factor(df_for_chord$DiseaseModel, levels = disease_counts$DiseaseModel)
df_for_chord$TaskArea <- factor(df_for_chord$TaskArea, levels = task_counts$TaskArea)

# Create and save bar plot for Disease Model
bar_plot_disease <- ggplot(df_for_chord, aes(x = DiseaseModel, y = Count, fill = DiseaseModel)) +
  geom_bar(stat = "identity") +
  scale_fill_manual(values = setNames(tailwind_colors[1:length(unique(df_for_chord$DiseaseModel))], levels(df_for_chord$DiseaseModel))) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 20) +  # Increase base font size further
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 20),  # Increase axis text size
    axis.title = element_text(size = 22),  # Increase axis title size
    legend.position = "none"
  ) +
  coord_flip() +
  labs(y = "Count", x = "Disease Model")
ggsave("bar_plot_disease_model_tw.png", bar_plot_disease, width = 14, height = 10, bg = "white")

# Create and save bar plot for AI/ML Task Area
bar_plot_task <- ggplot(df_for_chord, aes(x = TaskArea, y = Count, fill = TaskArea)) +
  geom_bar(stat = "identity") +
  scale_fill_manual(values = setNames(tailwind_colors[1:length(unique(df_for_chord$TaskArea))], levels(df_for_chord$TaskArea))) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 20) +  # Increase base font size further
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 20),  # Increase axis text size
    axis.title = element_text(size = 22),  # Increase axis title size
    legend.position = "none"
  ) +
  coord_flip() +
  labs(y = "Count", x = "AI/ML Task Area")
ggsave("bar_plot_task_area_tw.png", bar_plot_task, width = 14, height = 10, bg = "white")
