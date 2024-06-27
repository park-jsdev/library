# Load necessary libraries
library(tidyverse)
library(readxl)
library(dplyr)
library(ggplot2)
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
wrap_text <- function(text, width = 18) {
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

# Create and save bar plot for Disease Model
bar_plot_disease <- ggplot(df_for_chord, aes(x = DiseaseModel, y = Count, fill = DiseaseModel)) +
  geom_bar(stat = "identity") +
  scale_fill_manual(values = setNames(tailwind_colors[1:length(unique(df_for_chord$DiseaseModel))], levels(df_for_chord$DiseaseModel))) +  # Use the defined colors for the bar plot
  theme_minimal(base_size = 20) +  # Increase base font size further
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text = element_text(size = 20, face = "bold"),  # Increase and bold axis text
    axis.title = element_text(size = 22, face = "bold"),  # Increase and bold axis title
    legend.position = "none",
    axis.text.x = element_text(margin = margin(t = 10))  # Add space between x-axis labels and axis
  ) +
  scale_y_continuous(expand = expansion(mult = c(0, 0.1))) +  # Add space at the top of the bars
  coord_flip() +
  labs(y = "Count", x = "Disease Model")

# Save the bar plot
ggsave("bar_plot_disease_model_tw.png", bar_plot_disease, width = 16, height = 12, bg = "white")
