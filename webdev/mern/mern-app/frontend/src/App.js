import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import CssBaseline from '@material-ui/core/CssBaseline';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import FormPage from './components/FormPage';
import ViewRecordsPage from './components/ViewRecordsPage';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
}));

function App() {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <Router>
      <CssBaseline />
      <div className={classes.root}>
        <AppBar position="static">
          <Toolbar>
            <Typography variant="h6" className={classes.title}>
              Contact Management Dashboard
            </Typography>
            <Button color="inherit" aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
              Menu
            </Button>
            <Menu
              id="simple-menu"
              anchorEl={anchorEl}
              keepMounted
              open={Boolean(anchorEl)}
              onClose={handleClose}
            >
              <MenuItem onClick={handleClose} component={Router.Link} to="/add">Add Record</MenuItem>
              <MenuItem onClick={handleClose} component={Router.Link} to="/view">View Records</MenuItem>
            </Menu>
          </Toolbar>
        </AppBar>
        <Switch>
          <Route path="/add" component={FormPage} />
          <Route path="/view" component={ViewRecordsPage} />
          <Route path="/" exact component={FormPage} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;
