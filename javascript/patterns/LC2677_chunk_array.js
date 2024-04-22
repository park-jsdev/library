/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array}
 */
var chunk = function(arr, size) {
    if (arr.length == 0) return [];
    const res = [arr.slice(0, size)];
    res.push(...chunk(arr.slice(size), size)) // spread operator to avoid nested subarrays
    return res;
};
