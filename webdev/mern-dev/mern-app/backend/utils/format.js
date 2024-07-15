const formatPhoneNumberForStorage = (phone) => {
  return phone.replace(/\D/g, '');
};

const formatPhoneNumberForDisplay = (countryPrefix, localFormat) => {
  return `${countryPrefix} ${localFormat}`;
};

module.exports = { formatPhoneNumberForStorage, formatPhoneNumberForDisplay };
