export default {
  strIsNullOrBlank(str) {
    if (str == null || str == "")
      return true
    return false
  },
  checkStrPattern(str, pattern) {
    try {
      return pattern.test(str)
    } catch (e) {
      throw e
    }
    return false
  }
}