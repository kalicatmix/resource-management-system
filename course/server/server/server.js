var app = require("./_server/app")
var route = require("./_server/routes")
var jwt = require("./_server/token")
module.exports = {
    startServer() {
        app.addFilter(async (ctx, next) => {
            if(ctx.path!="/login"&&ctx.path!="/user/add")
           {
             var authorize = ctx.header['authorize']
             var uid = ctx.cookies.get("uid")
             await jwt.verifyToken(authorize,uid).then(async data=>{
                await next()
             }).catch(async err=>{
                // await next()
             })

        }else{   
           await next()    
        }
        })
        app.mapRoutes(route.routes)
        app.startApp()
    }

}