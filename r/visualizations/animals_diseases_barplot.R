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

# Read the dataset from the specified sheet for diseases
file_path <- "data.xlsx"
data_diseases <- read_excel(file_path, sheet = "diseases")

# Filter out NA values
data_diseases <- data_diseases %>%
  filter(!is.na(`Disease Model`))

# Count the occurrences of each disease model
counts_diseases <- data_diseases %>%
  count(`Disease Model`) %>%
  arrange(desc(n))

# Function to wrap text
wrap_labels <- function(x) {
  sapply(x, function(y) str_wrap(y, width = 20))
}

# Create the bar plot for diseases
bar_plot_diseases <- ggplot(counts_diseases, aes(x = reorder(wrap_labels(`Disease Model`), -n), y = n, fill = `Disease Model`)) +
  geom_bar(stat = "identity", color = "black", size = 2, width = 0.5) +  # Increase space between bars by reducing width
  scale_fill_manual(values = generate_custom_palette(nrow(counts_diseases))) +  # Use the custom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(size = 15),  # Increase axis text size
    axis.text.y = element_text(size = 20),  # Increase axis text size
    axis.title.x = element_text(size = 22, margin = margin(t = 20, r = 20, b = 50, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 22, margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(y = "Count", x = "Disease Model")

# Save the plot for diseases
ggsave("disease_model_bar_plot.png", bar_plot_diseases, width = 16, height = 12, bg = "white")

# Display the plot for diseases
print(bar_plot_diseases)
