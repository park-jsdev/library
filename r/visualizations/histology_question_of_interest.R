# Load necessary libraries
library(tidyverse)
library(readxl)
library(ggsci)
library(stringr)  # for text wrapping
library(cowplot)  # for arranging plots

# Define custom color palette with distinct colors for each category
custom_palette <- c(
  "Inflammation" = "#1F77B4",  # Blue
  "Emphysema" = "#FF7F0E",     # Orange
  "Fibrosis" = "#2CA02C",      # Green
  "Granuloma" = "#D62728",     # Red
  "Hemosiderin" = "#9467BD",   # Purple
  "Tissue" = "#8C564B",        # Brown
  "Collagen" = "#E377C2",      # Pink
  "Tumors" = "#7F7F7F",        # Gray
  "MLI" = "#BCBD22",           # Yellow
  "Quantification" = "#17BECF", # Cyan
  "Indices" = "#B2DF8A",       # Light Green
  "Ashcroft Scoring" = "#FDBF6F", # Light Orange
  "Ratio Systems" = "#CAB2D6", # Light Purple
  "Multi-Class Classifications" = "#6A3D9A", # Dark Purple
  "Stereology" = "#FF99D9",    # Light Pink
  "Binary Classifications" = "#A6CEE3" # Light Blue
)

# Read the dataset from the specified sheet
file_path <- "data.xlsx" # Adjust this path if necessary
data <- read_excel(file_path, sheet = "scoring")

# Filter out NA values for Scoring System Class
data_scoring <- data %>%
  filter(!is.na(`Scoring System Class`))

# Count the occurrences of each Scoring System Class
counts_scoring <- data_scoring %>%
  count(`Scoring System Class`) %>%
  arrange(desc(n))

# Function to wrap text
wrap_labels <- function(x) {
  sapply(x, function(y) str_wrap(y, width = 20))
}

# Create the horizontal bar plot for Scoring System Class
bar_plot_scoring <- ggplot(counts_scoring, aes(x = n, y = reorder(wrap_labels(`Scoring System Class`), n), fill = `Scoring System Class`)) +
  geom_bar(stat = "identity", color = "black", size = 1, width = 0.8) +  # Adjust bar border thickness and width
  scale_fill_manual(values = custom_palette) +  # Use the custom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(size = 24, face = "bold", color = "black"),  # Size of axis text
    axis.text.y = element_text(size = 24, face = "bold", color = "black"),  # Increase axis text size
    axis.title.x = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(x = "Count", y = "Scoring System Class")

# Save the plot
ggsave("scoring_system_class_bar_plot.png", bar_plot_scoring, width = 20, height = 12, bg = "white")

# Display the plot
print(bar_plot_scoring)


### Histological Evidence

# Filter out NA values for Histological Evidence Category
data_evidence <- data %>%
  filter(!is.na(`Histological Evidence Category`))

# Count the occurrences of each Histological Evidence Category
counts_evidence <- data_evidence %>%
  count(`Histological Evidence Category`) %>%
  arrange(desc(n))

# Create the horizontal bar plot for Histological Evidence Category
bar_plot_evidence <- ggplot(counts_evidence, aes(x = n, y = reorder(wrap_labels(`Histological Evidence Category`), n), fill = `Histological Evidence Category`)) +
  geom_bar(stat = "identity", color = "black", size = 1, width = 0.8) +  # Adjust bar border thickness and width
  scale_fill_manual(values = custom_palette) +  # Use the custom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(size = 24, face = "bold", color = "black"),  # Size of axis text
    axis.text.y = element_text(size = 24, face = "bold", color = "black"),  # Increase axis text size
    axis.title.x = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(x = "Count", y = "Histological Evidence Category")

# Save the plot
ggsave("histological_evidence_category_bar_plot.png", bar_plot_evidence, width = 20, height = 12, bg = "white")

# Display the plot
print(bar_plot_evidence)

# Combine the horizontal plots with a title
combined_plot <- plot_grid(bar_plot_evidence, bar_plot_scoring, ncol = 1, align = "v", labels = c("A", "B"))

# Add a title to the combined plot
title <- ggdraw() + 
  draw_label("Question of interest", fontface = 'bold', x = 0.5, hjust = 0.5, size = 48)

final_plot <- plot_grid(title, combined_plot, ncol = 1, rel_heights = c(0.1, 1))

# Save the combined plot
ggsave("combined_bar_plot_with_title2.png", final_plot, width = 24, height = 24, bg = "white")

# Display the combined plot
print(final_plot)
