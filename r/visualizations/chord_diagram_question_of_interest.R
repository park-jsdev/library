# Load necessary libraries
library(tidyverse)
library(readxl)
library(dplyr)
library(ggplot2)
library(png)
library(grid)
library(stringr)
library(cowplot)

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

# Sum the counts for each disease model and task area for sorting
disease_counts <- df_for_chord %>%
  group_by(DiseaseModel) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(desc(TotalCount))

task_counts <- df_for_chord %>%
  group_by(TaskArea) %>%
  summarise(TotalCount = sum(Count)) %>%
  arrange(desc(TotalCount))

# Reorder the data frames for plotting
df_for_chord$DiseaseModel <- factor(df_for_chord$DiseaseModel, levels = rev(disease_counts$DiseaseModel))
df_for_chord$TaskArea <- factor(df_for_chord$TaskArea, levels = rev(task_counts$TaskArea))

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

# Stacked bar plot for Disease Model
stacked_bar_disease <- ggplot(df_for_chord, aes(y = DiseaseModel, x = Count, fill = TaskArea)) +
  geom_bar(stat = "identity", color = "black", size = 1) +  # Add black borders with thicker size
  scale_fill_manual(values = tailwind_colors) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 20, face = "bold", color = "black"),  # Increase axis text size
    axis.title = element_text(size = 22, face = "bold", color = "black"),  # Increase axis title size
    legend.position = "right"
  ) +
  labs(x = "Count", y = "Disease Model", fill = "Task Area")

# Stacked bar plot for AI/ML Task Area
stacked_bar_task <- ggplot(df_for_chord, aes(y = TaskArea, x = Count, fill = DiseaseModel)) +
  geom_bar(stat = "identity", color = "black", size = 1) +  # Add black borders with thicker size
  scale_fill_manual(values = tailwind_colors) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 20, face = "bold", color = "black"),  # Increase axis text size
    axis.title = element_text(size = 22, face = "bold", color = "black"),  # Increase axis title size
    legend.position = "right"
  ) +
  labs(x = "Count", y = "AI/ML Task Area", fill = "Disease Model")

# Combine the plots with a title
combined_plot <- plot_grid(stacked_bar_disease, stacked_bar_task, ncol = 1, align = "v", labels = c("A", "B"))

# Add a title to the combined plot
title <- ggdraw() + 
  draw_label("Question of Interest", fontface = 'bold', x = 0.5, hjust = 0.5, size = 48)

final_plot <- plot_grid(title, combined_plot, ncol = 1, rel_heights = c(0.1, 1))

# Save the combined plot
ggsave("combined_bar_plot_with_title.png", final_plot, width = 16, height = 24, bg = "white")

# Display the combined plot
print(final_plot)
