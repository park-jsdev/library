const express = require('express');
const router = express.Router();
const { createForm, getFormById, getAllForms } = require('../controllers/formController');

router.route('/').post(createForm).get(getAllForms);
router.route('/:id').get(getFormById);

module.exports = router;
