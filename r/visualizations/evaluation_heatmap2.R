# Required Libraries
library(tidyverse)
library(ggplot2)
library(readxl)
library(dplyr)

# Data Preparation
data <- read_excel("data.xlsx", sheet = "evaluation")

# Clean the data by dropping rows with NA in key columns
data_clean <- data %>%
  drop_na(`AI/ML Technique`, `Evaluation Metric`, `Averaged Standardized Results`)

# Remove the Distance row entirely
data_clean <- data_clean %>%
  filter(`Evaluation Metric` != "Distance")

# Replace NA or missing values with 0 (for grey color)
data_clean$`Averaged Standardized Results`[is.na(data_clean$`Averaged Standardized Results`)] <- 0

# Fill missing combinations with 0
data_filled <- expand.grid(
  `AI/ML Technique` = unique(data_clean$`AI/ML Technique`),
  `Evaluation Metric` = unique(data_clean$`Evaluation Metric`)
) %>%
  left_join(data_clean, by = c("AI/ML Technique", "Evaluation Metric")) %>%
  replace_na(list(`Averaged Standardized Results` = 0))

# Convert to dataframe for ggplot
df_for_heatmap <- as.data.frame(data_filled)

# Create a custom color gradient
custom_gradient <- scale_fill_gradientn(
  colors = c("lightskyblue", "skyblue", "deepskyblue", "blue"),
  values = c(0, 0.5, 0.8, 1),
  na.value = "lightskyblue",
  name = "Average Result"
)

# Plot Heatmap
heatmap_plot <- ggplot(df_for_heatmap, aes(x = `Evaluation Metric`, y = `AI/ML Technique`, fill = `Averaged Standardized Results`)) +
  geom_tile(color = "white") +
  custom_gradient +
  # labs(title = "Heatmap of AI/ML Techniques vs. Evaluation Metrics", x = "Evaluation Metric", y = "AI/ML Technique", fill = "Average Result") +
  labs(x = "Evaluation Metric", y = "AI/ML Technique", fill = "Average Result") +
  theme_minimal() +
  theme(
    axis.text.x = element_text(angle = 45, hjust = 1, size = 24, face = "bold", color = "black"),
    axis.text.y = element_text(size = 24, face = "bold", color = "black"),
    axis.title.x = element_text(size = 32, face = "bold", color = "black"),
    axis.title.y = element_text(size = 32, face = "bold", color = "black"),
    plot.title = element_text(size = 35, face = "bold", color = "black"),
    legend.text = element_text(size = 18, face = "bold", color = "black"),
    legend.title = element_text(size = 20, face = "bold", color = "black", margin = margin(b = 20))
  ) +
  guides(fill = guide_colorbar(barwidth = 1, barheight = 20))

ggsave("heatmap_evaluation_plot.png", heatmap_plot, width = 20, height = 12, bg = "white")

# Display the plot
print(heatmap_plot)
