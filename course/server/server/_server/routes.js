var service = require("../../service/service")
var Router = require("koa-router")
var messageBuilder = require("./message")
var jwt = require("./token")
var session=require("./session")

var router = new Router()
var errorMessage = messageBuilder.buildErrorMessage()
//用户
router.post("/user/add", async (ctx) => {
    var user = ctx.query
    await service.getRepo().addUser(user).then(res => {
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ create: true })
            ctx.body = message
    }).catch(err => {
        ctx.body = errorMessage
    })
})

router.get("/user/update/:key/:value",async (ctx)=>{ 
    var param=ctx.params
    var uid= ctx.cookies.get('uid')
    var user=session.getSession(uid)
    if(!user)
    {
     ctx.body=errorMessage
    }else{
    user[param.key]=param.value
    await service.getRepo().updateUser(user).then(res=>{
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ updated: true })
            ctx.body = message
    }).catch(e=>{
       ctx.body=errorMessage
    })}
})

//登陆
router.get("/login",ctx=>{
ctx.body=errorMessage
})

router.post("/login", async (ctx) => {
    var user = ctx.query
    var message = errorMessage
    await service.getRepo().queryUser(user).then((res) => {
        var message
        if (res.err) {
            message = errorMessage
        }
        else {
            if (res.result.length < 1) {
                message = errorMessage
            }
            else {
                var token = jwt.addToken({ user: res.result[0] }, user.user)
                ctx.cookies.set("uid", user.user)
                session.addSession(user.user,res.result[0])
                message = messageBuilder.buildMessage({ token: token, uid: user.user })
              
            }
        }
        ctx.body=message
    }).catch(err=>{
        ctx.body=errorMessage
    })
    
})

//课程
router.post("/course/add", async (ctx) => {
    var param=ctx.query
    var uid= ctx.cookies.get('uid')
    var user=session.getSession(uid)
    if(!user)
    {
     ctx.body=errorMessage
    }else{
    course={name:param.course,user:user.user}
    await service.getRepo().addCourse(course).then(res=>{
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ added: true })
            ctx.body = message
    }).catch(e=>{
       ctx.body=errorMessage
    })}
})

router.post("/course/del", async (ctx) => {
    var param=ctx.query
    var uid= ctx.cookies.get('uid')
    var user=session.getSession(uid)
    if(!user)
    {
     ctx.body=errorMessage
    }else{
    course={name:param.course,user:user.user}
    await service.getRepo().removeCourse(course).then(res=>{
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ added: true })
            ctx.body = message
    }).catch(e=>{
       ctx.body=errorMessage
    })}
})

router.get("/course/query", async (ctx) => {
    var uid= ctx.cookies.get('uid')
    var user=session.getSession(uid)
    if(!user)
    {
     ctx.body=errorMessage
    }else{
    await service.getRepo().queryCourseByUser(user.user).then(res=>{
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ result:res.result})
            ctx.body = message
    }).catch(e=>{
       ctx.body=errorMessage
    })}
})

router.get("/type/query", async (ctx) => {
    await service.getRepo().queryCourseTypes().then(res=>{
        var message
        if (res.err)
            message = errorMessage
        else
            message = messageBuilder.buildMessage({ result:res.result})
            ctx.body = message
    }).catch(e=>{
       ctx.body=errorMessage
    })}

)


routes = router.routes()
module.exports = {
    routes
}