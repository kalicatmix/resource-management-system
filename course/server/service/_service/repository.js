var mysql = require("mysql")
var userModal = require("./userModal")

module.exports = {
    /*
     author:kalicat
     数据库repository
    */
    init() {
        this._pool = mysql.createPool({
            connectionLimit: 5,
            host: 'localhost',
            user: 'root',
            password: 'mysqlinnod',
            database: 'course',
            charset: 'utf8'
        })
    },
    errors: {
        ERROR_FILTER: 0xFF & 0xFF
    },
    _queryBySql(sql,callback) {

        if (undefined == this._pool)
            throw "please init repository first"

        this._pool.query(sql, (err, result) => {
            if(err)
            console.log(err)
            callback({err,result})
        })
    },
    _filterInput(input) {
        var filters = [/\sand\s/i, /\sor\s/i]
        for (item of filters) {
            if ((item.test(input)))
                return true
        }
        return false
    },
    _filterInputs(inputs) {
        for (input of inputs) {
            if (this._filterInput(input))
                return true
        }
        return false
    },
    getDataBasePool() {
        return this._pool
    },
    addCourseType(type) {
        return new Promise((resolve, reject) => {
            var sql = "insert into type values(?)"
            if (this._filterInput(type))
                reject()
            sql = mysql.format(sql, [type])
            this._queryBySql(sql, resolve)
        })
    },

    delCourseType(type) {
        return new Promise((resolve, reject) => {
            var sql = "delete from type where type=?"
            if (this._filterInputs(type))
                reject()
            sql = mysql.format(sql, [type])
            this._queryBySql(sql, resolve)
        })

    },
    queryCourseTypes() {
        return new Promise((resolve, reject) => {
            var sql = "select * from type"
            this._queryBySql(sql, resolve)
        })

    },
    addUser(user) {
        return new Promise((resolve, reject) => {
            var sql = "insert into user(name,user,pwd,email,luckynumber)values(?,?,?,?,?)"
            var items = [user.name, user.user, user.pwd, user.email, user.luckynumber]
            if (this._filterInputs(items))
                reject()
            sql = mysql.format(sql, items)
            this._queryBySql(sql, resolve)
        })

    },
    updateUser(user) {
        return new Promise((resolve, reject) => {
            var sql = "update user set name=?,user=?,pwd=?,email=?,luckynumber=? where user=? and pwd=?"
            var items = [user.name, user.user, user.pwd, user.email, user.luckynumber, user.user, user.pwd]
            if (this._filterInputs(items))
                reject()
            sql = mysql.format(sql, items)
            this._queryBySql(sql, resolve)

        })

    },
    queryUser(user) {
        return new Promise((resolve, reject) => {
            var sql = "select * from user where user=? and pwd=?"
            var items = [user.user, user.pwd]
            if (this._filterInputs(items))
                reject()
            sql = mysql.format(sql, items)
            this._queryBySql(sql,resolve)

        })
    },
    addCourse(course) {
        return new Promise((resolve, reject) => {
            var sql = "insert into  course(user,name) values(?,?)"
            var items = [course.user, course.name]
            if (this._filterInputs(items))
                reject()
            sql = mysql.format(sql, items)
            this._queryBySql(sql, resolve)

        })

    },
    removeCourse(course) {
        return new Promise((resolve, reject) => {
            var sql = "delete from course where user=? and name=?"
            var items = [course.user, course.name]
            if (this._filterInputs(items))
                reject()
            sql = mysql.format(sql, items)
            this._queryBySql(sql, resolve)
        })

    },
    queryCourseByUser(user) {
        return new Promise((resolve, reject) => {
            var sql = "select * from course where user=?"
            if (this._filterInput(user))
                reject()
            sql = mysql.format(sql, [user])
            this._queryBySql(sql, resolve)
        })

    },
}