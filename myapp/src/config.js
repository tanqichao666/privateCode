// const API = 'http://localhost:8763';
//const API = 'http://localhost:8769/api';
const API ='/api'

const config = {
    api: {
        getUsers: API+'/users/queryAll',
        deleteUser: API+'/users/remove',
        createUser: API+'/users/add',
        editUser: API+'/users/editUser',
    }
}
export default config;