# Load necessary libraries
library(tidyverse)
library(readxl)
library(ggsci)
library(stringr)  # for text wrapping

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

# Create the bar plot for animals
bar_plot_animals <- ggplot(counts_animals, aes(x = reorder(wrap_labels(`Animals studied`), -n), y = n, fill = `Animals studied`)) +
  geom_bar(stat = "identity", color = "black", size = 1, width = 0.8) +  # Adjust bar border thickness and width
  scale_fill_manual(values = generate_custom_palette(nrow(counts_animals))) +  # Use the custom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(angle = 45, hjust = 1, size = 24, face = "bold", color = "black"),  # Angle and size of axis text
    axis.text.y = element_text(size = 24, face = "bold", color = "black"),  # Increase axis text size
    axis.title.x = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 32, face = "bold", color = "black", margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(y = "Count", x = "Animals studied")

# Save the plot for animals
ggsave("animals_studied_bar_plot.png", bar_plot_animals, width = 16, height = 12, bg = "white")

# Display the plot for animals
print(bar_plot_animals)
