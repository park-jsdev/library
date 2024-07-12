import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Container, AppBar, Toolbar, Typography, Menu, MenuItem, IconButton } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import FormPage from './components/FormPage';
import ViewRecordsPage from './components/ViewRecordsPage';

const App = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleMenuClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  return (
    <Router>
      <Container>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" style={{ flexGrow: 1 }}>
              Contact Management Dashboard
            </Typography>
            <IconButton
              edge="start"
              color="inherit"
              aria-label="menu"
              onClick={handleMenuClick}
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="simple-menu"
              anchorEl={anchorEl}
              keepMounted
              open={Boolean(anchorEl)}
              onClose={handleMenuClose}
            >
              <MenuItem onClick={handleMenuClose} component="a" href="/">
                Add Contact
              </MenuItem>
              <MenuItem onClick={handleMenuClose} component="a" href="/view-records">
                View Contacts
              </MenuItem>
            </Menu>
          </Toolbar>
        </AppBar>
        <Switch>
          <Route exact path="/" component={FormPage} />
          <Route path="/view-records" component={ViewRecordsPage} />
        </Switch>
      </Container>
    </Router>
  );
};

export default App;
