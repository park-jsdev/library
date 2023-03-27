' This function checks a source sheet to see if requests are overdue, and creates a message for the associated user

Sub CheckTickets()
    ' Define variables
    Dim sourceSheet As Worksheet
    Dim targetSheet As Worksheet
    Dim lastRow As Long
    Dim userColumn As Long
    Dim dueDateColumn As Long
    Dim dateClosedColumn As Long
    Dim ticketNumberColumn As Long
    Dim affectedUserColumn As Long
    Dim lastActionDateColumn As Long
    Dim incidentRefNumberColumn As Long
    Dim shortDescColumn As Long
    Dim userDict As Object
    Dim user As String
    Dim overdueCount As Long
    Dim msg As String
    
    ' Set variables
    Set sourceSheet = ThisWorkbook.Worksheets("Source Sheet")
    Set targetSheet = ThisWorkbook.Worksheets("Target Sheet")
    lastRow = sourceSheet.Cells(sourceSheet.Rows.Count, "A").End(xlUp).Row ' Assumes data starts in cell A1
    userColumn = 2
    dueDateColumn = 5
    dateClosedColumn = 4
    ticketNumberColumn = 1
    affectedUserColumn = 3
    lastActionDateColumn = 6
    incidentRefNumberColumn = 7
    shortDescColumn = 8
    
    ' Initialize dictionary to store user and overdue count
    Set userDict = CreateObject("Scripting.Dictionary")
    
    ' Loop through rows in source sheet
    For i = 2 To lastRow ' Assumes header row is in row 1
        user = sourceSheet.Cells(i, userColumn).Value
        If Not userDict.Exists(user) Then
            overdueCount = 0
            userDict.Add user, overdueCount
        End If
        
        ' Check if ticket is overdue
        If sourceSheet.Cells(i, dateClosedColumn).Value = "" And sourceSheet.Cells(i, dueDateColumn).Value < Date Then
            overdueCount = userDict(user)
            overdueCount = overdueCount + 1
            userDict(user) = overdueCount

            ' Add additional info to message
            msg = msg & "Ticket #" & sourceSheet.Cells(i, ticketNumberColumn).Value & ": "
            msg = msg & sourceSheet.Cells(i, shortDescColumn).Value & " for " & sourceSheet.Cells(i, affectedUserColumn).Value
            msg = msg & " (Last action: " & sourceSheet.Cells(i, lastActionDateColumn).Value & ", Ref #: " & sourceSheet.Cells(i, incidentRefNumberColumn).Value & ")" & vbNewLine
        End If
    Next i
    
    ' Write results to target sheet
    targetSheet.Range("A2:C" & userDict.Count + 1).ClearContents ' Clear previous results
    For i = 0 To userDict.Count - 1
        user = userDict.Keys(i)
        overdueCount = userDict(user)
        If overdueCount > 0 Then
            msg = "There are " & overdueCount & " tickets overdue for " & user & ":" & vbNewLine & vbNewLine & msg
        Else
            msg = "There are no overdue tickets for " & user & "." & vbNewLine

        End If
        targetSheet.Cells(i + 2, 1).Value = user
        targetSheet.Cells(i + 2, 2).Value = overdueCount
        targetSheet.Cells(i + 2, 3).Value = msg
    Next i
End Sub
