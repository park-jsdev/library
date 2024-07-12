import React, { useState, useEffect } from 'react';
import { Box, TextField, Button, Typography, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import axios from 'axios';
import ContactList from './ContactList';
import { CSVLink } from 'react-csv';

const ViewRecordsPage = () => {
  const [contacts, setContacts] = useState([]);
  const [searchName, setSearchName] = useState('');
  const [filterGroup, setFilterGroup] = useState('');
  const [groups, setGroups] = useState([]);

  useEffect(() => {
    fetchContacts();
  }, []);

  const fetchContacts = async () => {
    const response = await axios.get('/api/forms');
    setContacts(response.data);
    const uniqueGroups = [...new Set(response.data.map(contact => contact.group))];
    setGroups(uniqueGroups);
  };

  const handleSearchName = (e) => {
    setSearchName(e.target.value);
  };

  const handleFilterGroup = (e) => {
    setFilterGroup(e.target.value);
  };

  return (
    <Box className="container">
      <Box className="header">
        <Typography variant="h4">Contact Management Dashboard</Typography>
      </Box>
      <Box className="navbar">
        <a href="/add-new-contact">Add New Contact</a>
        <a href="/view-contacts">View Contacts</a>
      </Box>
      <Box className="form-container">
        <Box className="page-title">
          <Typography variant="h5">View Contacts</Typography>
        </Box>
        <Box className="search-filter-container">
          <TextField label="Search by Name" variant="outlined" value={searchName} onChange={handleSearchName} />
          <FormControl variant="outlined">
            <InputLabel>Filter Group</InputLabel>
            <Select value={filterGroup} onChange={handleFilterGroup}>
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {groups.map((group, index) => (
                <MenuItem key={index} value={group}>{group}</MenuItem>
              ))}
            </Select>
          </FormControl>
          <Button variant="contained" color="primary">
            <CSVLink data={contacts} filename="contacts.csv" className="btn">Export to CSV</CSVLink>
          </Button>
        </Box>
        <ContactList contacts={contacts} searchName={searchName} filterGroup={filterGroup} />
      </Box>
    </Box>
  );
};

export default ViewRecordsPage;
