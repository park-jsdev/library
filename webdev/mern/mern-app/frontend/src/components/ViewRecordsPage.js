import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Container,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
  Button,
} from '@mui/material';

const ViewRecordsPage = () => {
  const [records, setRecords] = useState([]);
  const [search, setSearch] = useState('');
  const [filter, setFilter] = useState('');

  useEffect(() => {
    axios
      .get('http://localhost:5000/api/forms')
      .then((response) => {
        setRecords(response.data);
      })
      .catch((error) => {
        console.error('There was an error fetching the records!', error);
      });
  }, []);

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:5000/api/forms/${id}`)
      .then(() => {
        setRecords(records.filter((record) => record._id !== id));
      })
      .catch((error) => {
        console.error('There was an error deleting the record!', error);
      });
  };

  const filteredRecords = records.filter(
    (record) =>
      record.name.toLowerCase().includes(search.toLowerCase()) &&
      record.group.toLowerCase().includes(filter.toLowerCase())
  );

  return (
    <Container>
      <TextField
        label="Search by Name"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ marginBottom: 20 }}
      />
      <TextField
        label="Filter by Group"
        value={filter}
        onChange={(e) => setFilter(e.target.value)}
        style={{ marginBottom: 20, marginLeft: 10 }}
      />
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Group</TableCell>
              <TableCell>Valid</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filteredRecords.map((record) => (
              <TableRow key={record._id}>
                <TableCell>{record.name}</TableCell>
                <TableCell>{record.email}</TableCell>
                <TableCell>{record.phone}</TableCell>
                <TableCell>{record.group}</TableCell>
                <TableCell>{record.isValid ? 'Yes' : 'No'}</TableCell>
                <TableCell>
                  <Button color="secondary" onClick={() => handleDelete(record._id)}>
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Container>
  );
};

export default ViewRecordsPage;
