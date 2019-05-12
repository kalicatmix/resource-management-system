var Koa = require("koa")
module.exports = {
    app: new Koa(),
    startApp() {
        console.log(Date() + "server start at localhost:3000")
        this.app.listen(3000)
    },
    mapRoutes(routes) {
        this.app.use(routes)
    },
    addFilter(middleware){
        this.app.use(middleware)
    }

}