import React from 'react';

import {PropTypes} from 'prop-types';
// 引入 connect 工具函数
import { connect } from 'dva';

// Users 的 Presentational Component
// 暂时都没实现
import UserSearch from '../components/Users/UserSearch';
import UserList from '../components/Users/UserList';
import UserModal from '../components/Users/UserModal';

// 引入对应的样式
// 可以暂时新建一个空的
import styles from './Users.less';


const  Users =(props) => {
  const{ location, dispatch, users} =props ;

  const {
    loading, list, total, current,
     currentItem, modalVisible, modalType
    } = users;

  const userSearchProps={
    dispatch,
    onSearch,
    onAdd,
    filter:{
      ...location.query
    }
  };
  const userListProps={
		dataSource: list,
		total,
		loading,
    current,
    doDelect,
    willEdit,
    pageChange,
    onShowSizeChange
	};
  const userModalProps={visible:modalVisible,item:modalType=='create'?{}:currentItem,
  onSave,
  onEdit,
  onCancel,
  title:getTitle(modalType)};

  
  function onShowSizeChange(current, pageSize) {
    const {query:params} = location;
    dispatch({
      type:'users/query',
      payload:{
        pageSize,
        ...params
      }
    })
  }
  function pageChange(current, pageSize) {
    const {query:params} = location;
    const pageNo = current;
    debugger
    dispatch({
      type:'users/query',
      payload:{
        pageNo,
        pageSize,
        ...params
      }
    })
  }

  function willEdit(record){
    dispatch({
      type:'users/showModal',
      payload:{
        currentItem :record,
        modalType : 'update'
      }
    })
  }

  function doDelect(id){
    dispatch({
      type:'users/delete',
      payload:{
        params :id
      }
    })
  }

  function onEdit(params){
    dispatch({
      type:'users/update',
      payload:{
        params :params
      }
    })
    onCancel();
  }

  function onSave(params){
    dispatch({
      type:'users/create',
      payload:{
        params :params
      }
    })
    onCancel();
  }

  function onCancel(){
    dispatch({
      type:'users/hideModal'
    })
  }

  function onSearch(params){
    location.query = params;
    for(let item of Object.keys(params) ){
      if(params[item]==""){
        params[item]=undefined
      }
    }
    dispatch({
      type:'users/query',
      payload:{
        params :params
      }
    })
  }

  function getTitle(type){
    if (type === "create"){
      return "新增用户";
    }else{
      return "编辑用户";
    }
  }

  function onAdd(params) {
    dispatch({
      type:'users/showModal',
      payload:{
        params :{}
      }
    })
  }


  return (
    <div className={styles.normal}>
      {/* 用户筛选搜索框 */}
      <UserSearch {...userSearchProps} />
      {/* 用户信息展示列表 */}
      <UserList {...userListProps} />
      {/* 添加用户 & 修改用户弹出的浮层 */}
      <UserModal {...userModalProps} />
    </div>
  );
}

Users.propTypes = {
  users: PropTypes.object,
};

// 指定订阅数据，这里关联了 users
function mapStateToProps({ users }) {
  return {users};
}

// 建立数据关联关系
export default connect(mapStateToProps)(Users);