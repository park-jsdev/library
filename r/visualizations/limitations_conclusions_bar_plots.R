# Load necessary libraries
library(tidyverse)
library(readxl)
library(ggsci)
library(stringr)  # for text wrapping

# Read the dataset from the specified sheet
file_path <- "data.xlsx" # Adjust this path if necessary
data <- read_excel(file_path, sheet = "limitations")

# Filter out NA values
data <- data %>%
  filter(!is.na(`Limitation Patterns`))

# Count the occurrences of each Limitation Pattern
counts <- data %>%
  count(`Limitation Patterns`) %>%
  arrange(desc(n))

# Function to wrap text
wrap_labels <- function(x) {
  sapply(x, function(y) str_wrap(y, width = 20))
}

# Create the bar plot
bar_plot <- ggplot(counts, aes(x = reorder(wrap_labels(`Limitation Patterns`), -n), y = n, fill = `Limitation Patterns`)) +
  geom_bar(stat = "identity", color = "black", size = 2, width = 0.7) +  # Add black borders with thicker size and thinner bars
  scale_fill_locuszoom() +  # Use the ggsci locuszoom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(size = 20),  # Increase axis text size
    axis.text.y = element_text(size = 20),  # Increase axis text size
    axis.title.x = element_text(size = 22, margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 22, margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(y = "Count", x = "Limitation Patterns")

# Save the plot
ggsave("limitation_patterns_bar_plot.png", bar_plot, width = 16, height = 12, bg = "white")

# Display the plot
print(bar_plot)


### Conclusions

# Filter out NA values
data <- data %>%
  filter(!is.na(`Conclusion Patterns`))

# Count the occurrences of each Conclusion Pattern
counts <- data %>%
  count(`Conclusion Patterns`) %>%
  arrange(desc(n))

# Function to wrap text
wrap_labels <- function(x) {
  sapply(x, function(y) str_wrap(y, width = 20))
}

# Create the bar plot
bar_plot <- ggplot(counts, aes(x = reorder(wrap_labels(`Conclusion Patterns`), -n), y = n, fill = `Conclusion Patterns`)) +
  geom_bar(stat = "identity", color = "black", size = 2, width = 0.3) +  # Further reduce bar width for more space
  scale_fill_locuszoom() +  # Use the ggsci locuszoom color palette
  theme_minimal(base_size = 20) +  # Increase base font size
  theme(
    panel.background = element_rect(fill = "white", color = NA),  # White background
    plot.background = element_rect(fill = "white", color = NA),   # White background for entire plot
    panel.grid = element_blank(),  # Remove grid lines
    axis.text.x = element_text(size = 20),  # Increase axis text size
    axis.text.y = element_text(size = 20),  # Increase axis text size
    axis.title.x = element_text(size = 22, margin = margin(t = 20, r = 20, b = 50, l = 20)),  # Increase axis title size and margin
    axis.title.y = element_text(size = 22, margin = margin(t = 20, r = 20, b = 20, l = 20)),  # Increase axis title size and margin
    legend.position = "none"  # Remove legend
  ) +
  labs(y = "Count", x = "Conclusion Patterns")

# Save the plot
ggsave("conclusion_patterns_bar_plot.png", bar_plot, width = 20, height = 12, bg = "white")

# Display the plot
print(bar_plot)