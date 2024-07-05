# Load necessary libraries
library(igraph)
library(readxl)
library(dplyr)

# Read the dataset from the specified sheet
file_path <- "data.xlsx" # Adjust this path if necessary
data <- read_excel(file_path, sheet = "limitations")

# Filter out rows with N/A or blank values in Limitations Patterns and AI/ML Task
data <- data %>%
  filter(!is.na(`Limitation Patterns`), `Limitation Patterns` != "", 
         !is.na(`AI/ML Task`), `AI/ML Task` != "")

# Extract relevant columns
edges <- data %>% 
  select(`AI/ML Task`, `Limitation Patterns`)

# Create a graph from the edges
g <- graph_from_data_frame(edges, directed = FALSE)

# Define colors for the nodes
V(g)$color <- ifelse(V(g)$name %in% data$`AI/ML Task`, "blue", "red")

# Plot the graph
png("network_plot.png", width = 1600, height = 1200, res = 150)
plot(g, layout = layout_in_circle, vertex.size = 30, vertex.label.cex = 0.8, vertex.label.color = "black", edge.color = "grey", edge.width = 1)
dev.off()
