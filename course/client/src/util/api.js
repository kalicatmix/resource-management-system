var baseUrl="http://localhost:3000/"
module.exports={
    login:baseUrl+"login",
    addUser:baseUrl+"user/add",
    updateUser:baseUrl+"user/update",
    updateUserPwd:baseUrl+"user/update/pwd",
    queryUserInfo:baseUrl+"user/query",
    addCourse:baseUrl+"course/add",
    delCourse:baseUrl+"course/del",
    queryCourse:baseUrl+"course/query",
    queryCourseSize:baseUrl+"course/size",
    queryType:baseUrl+"type/query",
    error:0xff,
    success:0,
}