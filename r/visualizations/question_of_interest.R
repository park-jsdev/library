# Load necessary libraries
library(tidyverse)
library(readxl)
library(ggsci)
library(stringr)  # for text wrapping
library(cowplot)  # for arranging plots

# Define a function to generate a custom color palette with distinct colors for each category
generate_custom_palette <- function(n) {
  colors <- c(
    "#1F77B4",  # Blue
    "#FF7F0E",  # Orange
    "#2CA02C",  # Green
    "#D62728",  # Red
    "#9467BD",  # Purple
    "#8C564B",  # Brown
    "#E377C2",  # Pink
    "#7F7F7F",  # Gray
    "#BCBD22",  # Yellow
    "#17BECF",  # Cyan
    "#B2DF8A",  # Light Green
    "#FDBF6F",  # Light Orange
    "#CAB2D6",  # Light Purple
    "#6A3D9A",  # Dark Purple
    "#FF99D9",  # Light Pink
    "#A6CEE3"   # Light Blue
  )
  rep(colors, length.out = n)
}

# Read the dataset from the specified sheet for animals
file_path <- "data.xlsx"
data_animals <- read_excel(file_path, sheet = "animals")

# Filter out NA values
data_animals <- data_animals %>%
  filter(!is.na(`Animals studied`))

# Count the occurrences of each animal model
counts_animals <- data_animals %>%
  count(`Animals studied`) %>%
  arrange(desc(n))

# Function to wrap text
wrap_labels <- function(x) {
  sapply(x, function(y) str_wrap(y, width = 20))
}

# Create the horizontal bar plot for animals
bar_plot_animals <- ggplot(counts_animals, aes(x = n, y = reorder(wrap_labels(`Animals studied`), n), fill = `Animals studied`)) +
  geom_bar(stat = "identity", color = "black", size = 1, width = 0.8) +  # Adjust bar border thickness and width
  scale_fill_manual(values = generate_custom_palette(nrow(counts_animals))) +  # Use the custom color palette
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
  labs(x = "Count", y = "Animals studied")

# Read the dataset from the specified sheet for diseases
data_diseases <- read_excel(file_path, sheet = "diseases")

# Filter out NA values
data_diseases <- data_diseases %>%
  filter(!is.na(`Disease Model`))

# Count the occurrences of each disease model
counts_diseases <- data_diseases %>%
  count(`Disease Model`) %>%
  arrange(desc(n))

# Create the horizontal bar plot for diseases
bar_plot_diseases <- ggplot(counts_diseases, aes(x = n, y = reorder(wrap_labels(`Disease Model`), n), fill = `Disease Model`)) +
  geom_bar(stat = "identity", color = "black", size = 1, width = 0.8) +  # Adjust bar border thickness and width
  scale_fill_manual(values = generate_custom_palette(nrow(counts_diseases))) +  # Use the custom color palette
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
  labs(x = "Count", y = "Disease Model")

# Combine the plots with a title
combined_plot <- plot_grid(bar_plot_animals, bar_plot_diseases, ncol = 1, align = "v", labels = c("A", "B"))

# Add a title to the combined plot
title <- ggdraw() + 
  draw_label("Question of interest", fontface = 'bold', x = 0.5, hjust = 0.5, size = 48)

final_plot <- plot_grid(title, combined_plot, ncol = 1, rel_heights = c(0.1, 1))

# Save the combined plot
ggsave("combined_bar_plot_with_title.png", final_plot, width = 16, height = 24, bg = "white")

# Display the combined plot
print(final_plot)
