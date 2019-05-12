var repository = require("./_service/repository")
var courseModal = require("./_service/courseModal")
var userModal = require("./_service/userModal")
module.exports = {
    getRepo() {
        repository.init()
        return repository
    },
    newCourseModal() {
        return courseModal.newModal()
    },
    newUserModal() {
        return userModal.newModal()
    }
}