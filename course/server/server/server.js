var app = require("./_server/app")
var route = require("./_server/routes")
var jwt = require("./_server/token")
var bodyparser=require("koa-bodyparser")
var message=require("./_server/message")
module.exports = {
    startServer() {
        app.addFilter(async (ctx,next)=>{
            ctx.set({
                "Access-Control-Allow-Credentials": "true",
                "Access-Control-Allow-Headers": "Content-type,authorize",
                "Access-Control-Allow-Methods":"POST,GET,DEL,PUT",
                "Access-Control-Allow-Origin":"http://localhost:8080",
            })
            if(ctx.method=="OPTIONS"){
                ctx.body=""
            }
            await next()
        } )
        app.addFilter(bodyparser())
        app.addFilter(async (ctx, next) => {
            if(ctx.path!="/login"&&ctx.path!="/user/add")
           {
             var authorize =await ctx.header['authorize']
             var uid =await ctx.cookies.get("uid")
             await jwt.verifyToken(authorize,uid).then(async data=>{
                await next()
             }).catch(async err=>{
                //await next()
                ctx.body=message.buildErrorMessage()
             })
        }else{   
           await next()    
        }
        })
        app.mapRoutes(route.routes)
        app.startApp()
    }

}