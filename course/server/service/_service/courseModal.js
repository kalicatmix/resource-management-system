module.exports = {
    modal: {
        "name": null,
        "user": null,
    },
    newModal() {
        return Object.create(this.modal)
    }
}