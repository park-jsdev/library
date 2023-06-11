# Reading from CSV File

# Set current working directory.
# setwd("/path/com")

# Get and print current working directory.
print(getwd())

data <- read.csv("input.csv")
print(data)

# Analyze the CSV File
print(is.data.frame(data))
print(ncol(data))
print(nrow(data))

# Get the max salary from data frame.
sal <- max(data$salary)
print(sal)

# Get the person detail having max salary.
retval <- subset(data, salary == max(salary))
print(retval)

# Get all the people working in IT department.
retval <- subset( data, dept == "IT")
print(retval)

# Get the persons in IT department whose salary is greater than 600
info <- subset(data, salary > 600 & dept == "IT")
print(info)

# Get the people who joined on or after 2014.
retval <- subset(data, as.Date(start_date) > as.Date("2014-01-01"))
print(retval)

# Writing into a CSV File

# Write filtered data into a new file.
write.csv(retval,"output.csv")
newdata <- read.csv("output.csv")
print(newdata)

# Write filtered data into a new file.
write.csv(retval,"output.csv", row.names = FALSE)
newdata <- read.csv("output.csv")
print(newdata)


