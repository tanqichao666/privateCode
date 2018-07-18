import request from '../utils/request';
import config from '../config'

export function query({pagination:{pageNo:pageNo ,pageSize:pageSize,name:name,age:age ,address:address}}) {
    console.log(config.api.getUsers);
    const pagination={pageNo:pageNo ,pageSize:pageSize,name:name,age:age ,address:address};
    console.log(pagination);
  return request(`${config.api.getUsers}`, {method:'post',headers: {'Content-Type': 'application/json'},body: JSON.stringify(pagination)});
}

export function deleteData(id) {
  console.log(id.params);
return request(`${config.api.deleteUser}`+"?id="+id.params, {method:'get',headers: {'Content-Type': 'application/json'}});
}

export function createUser(params) {
  debugger
  console.log(params.params);
return request(`${config.api.createUser}`, {method:'post',headers: {'Content-Type': 'application/json'},body: JSON.stringify(params.params)});
}

export function editUser(params) {
  debugger
  console.log(params.params);
return request(`${config.api.editUser}`, {method:'post',headers: {'Content-Type': 'application/json'},body: JSON.stringify(params.params)});
}