# Load necessary libraries
library(tidyverse)
library(readxl)
library(dplyr)
library(circlize)
library(ggplot2)
library(png)
library(grid)
library(stringr)

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

# Wrap long text for better display
wrap_text <- function(text, width = 8) {
  str_wrap(text, width = width)
}
df_for_chord$DiseaseModel <- wrap_text(df_for_chord$DiseaseModel)
df_for_chord$TaskArea <- wrap_text(df_for_chord$TaskArea)

# Prepare the data for the chord diagram
# Create a matrix from the dataframe
chord_matrix <- xtabs(Count ~ DiseaseModel + TaskArea, data = df_for_chord)

# Define the Tailwind color palette
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
png("chord_diagram_tw.png", width = 2200, height = 2200, bg = "white")  # Adjust canvas size
circos.clear()
circos.par(
  gap.after = c(rep(1, nrow(chord_matrix) - 1), 15, rep(1, ncol(chord_matrix) - 1), 15),  # Reduce gaps between sectors
  start.degree = 90,
  track.margin = c(0.01, 0.01),  # Adjust the margin between tracks
  canvas.xlim = c(-1.05, 1.05),  # Adjust the canvas limits to maximize white space usage
  canvas.ylim = c(-1.05, 1.05)   # Adjust the canvas limits to maximize white space usage
)
chordDiagram(
  chord_matrix,
  transparency = 0.5,
  annotationTrack = "grid",
  preAllocateTracks = list(track.height = 0.15),  # Adjust track height to create space for text
  grid.col = category_colors  # Use the defined colors for the grid
)
circos.track(
  track.index = 1,
  panel.fun = function(x, y) {
    sector.name = get.cell.meta.data("sector.index")
    circos.text(CELL_META$xcenter, CELL_META$ylim[1] + 0.1, sector.name,  # Increase the distance between text and border
                facing = "clockwise", niceFacing = TRUE, adj = c(0, 0.5), cex = 4, font = 2)  # Increase and bold text
  },
  bg.border = NA
)
dev.off()

# Sum the counts for each disease model and task area for sorting
disease_counts <- df_for_chord %>%
  group_by(DiseaseModel) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(TotalCount)  # Sort in ascending order

task_counts <- df_for_chord %>%
  group_by(TaskArea) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(TotalCount)  # Sort in ascending order

# Create data frames for plotting
df_disease <- df_for_chord %>%
  group_by(DiseaseModel) %>%
  summarise(Count = sum(Count))

df_task <- df_for_chord %>%
  group_by(TaskArea) %>%
  summarise(Count = sum(Count))

# Create and save bar plot for Disease Model
bar_plot_disease <- ggplot(df_disease, aes(x = reorder(DiseaseModel, Count), y = Count, fill = DiseaseModel)) +
  geom_bar(stat = "identity", color = "black", size = 1, show.legend = FALSE) +  # Add black borders to bars and increase border thickness
  scale_fill_manual(values = setNames(tailwind_colors[1:length(unique(df_disease$DiseaseModel))], levels(df_disease$DiseaseModel))) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 25) +  # Increase base font size further
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 25),  # Increase axis text size
    axis.title = element_text(size = 28),  # Increase axis title size
    legend.position = "none",
    axis.title.x = element_text(margin = margin(t = 20)),  # Increase margin between x-axis title and plot
    axis.title.y = element_text(margin = margin(r = 20))   # Increase margin between y-axis title and plot
  ) +
  coord_flip() +
  labs(y = "Count", x = "Disease Model")
ggsave("bar_plot_disease_model_tw.png", bar_plot_disease, width = 16, height = 12, bg = "white")

# Create and save bar plot for AI/ML Task Area
bar_plot_task <- ggplot(df_task, aes(x = reorder(TaskArea, Count), y = Count, fill = TaskArea)) +
  geom_bar(stat = "identity", color = "black", size = 1, show.legend = FALSE) +  # Add black borders to bars and increase border thickness
  scale_fill_manual(values = setNames(tailwind_colors[1:length(unique(df_task$TaskArea))], levels(df_task$TaskArea))) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 25) +  # Increase base font size further
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 25),  # Increase axis text size
    axis.title = element_text(size = 28),  # Increase axis title size
    legend.position = "none",
    axis.title.x = element_text(margin = margin(t = 20)),  # Increase margin between x-axis title and plot
    axis.title.y = element_text(margin = margin(r = 20))   # Increase margin between y-axis title and plot
  ) +
  coord_flip() +
  labs(y = "Count", x = "AI/ML Task Area")
ggsave("bar_plot_task_area_tw.png", bar_plot_task, width = 16, height = 12, bg = "white")
