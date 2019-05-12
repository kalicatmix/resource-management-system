module.exports = {
   modal:{"id": null,
    "name": null,
    "user": null,
    "pwd": null,
    "email": null,
    "luckynumber": null
},
newModal(){
    return Object.create(this.modal)
}
}