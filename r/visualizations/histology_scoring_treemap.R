# Load necessary libraries
library(treemap)
library(readxl)
library(dplyr)

# Read the dataset from the specified sheet
file_path <- "data.xlsx" # Adjust this path if necessary
data <- read_excel(file_path, sheet = "scoring")

# Filter out rows with NA values in key columns
data <- data %>%
  filter(!is.na(`AI/ML Technique`), !is.na(`Scoring System Class`), !is.na(`Histological Evidence Category`))

# Count occurrences of each combination
data_counts <- data %>%
  count(`AI/ML Technique`, `Scoring System Class`, `Histological Evidence Category`)

# Create the treemap and save it
png("treemap_plot.png", width = 2000, height = 1200)  # Adjust the size as necessary

# Plot the treemap
treemap(
  data_counts,
  index = c("AI/ML Technique", "Scoring System Class", "Histological Evidence Category"),
  vSize = "n",  # Using the count of occurrences as the size
  draw = TRUE,
  title = "Treemap of AI/ML Techniques, Scoring Systems, and Histological Evidence",
  palette = "Set3",
  border.col = "black",
  fontsize.labels = 12,
  align.labels = list(c("center", "center"), c("center", "center"), c("right", "bottom")),
  overlap.labels = 0.5,
  inflate.labels = FALSE
)

# Close the graphics device
dev.off()
