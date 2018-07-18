import * as userservice from '../services/userservice';

export default {
  namespace: 'users',

  state: {
    list: [],
    total: null,
    pageSize: null,
    loading: false, // 控制加载状态
    current: null, // 当前分页信息
    currentItem: {}, // 当前操作的用户对象
    modalVisible: false, // 弹出窗的显示状态
    modalType: 'create', // 弹出窗的类型（添加用户，编辑用户）
  },

  subscriptions: {
    setup({ dispatch, history }) {
      history.listen(location => {
        if (location.pathname === '/users') {
          dispatch({
            type: 'query',
            payload: {}
          });
        }
      });
    },
  },

  effects: {
    *query({ payload: {pageNo = '1',pageSize= '10',params} }, { select, call, put }) {
      yield put({ type: 'showLoading' });
      const name = params===undefined?'':params.name===undefined?'':params.name;
      const age = params===undefined?'':params.age ===undefined?'':params.age;
      const address = params===undefined?'':params.address===undefined?'':params.address;
      const data = yield call(userservice.query,{pagination:{pageNo:pageNo ,pageSize:pageSize,name:name,age : age ,address:address}});
      if (data) {
        yield put({
          type: 'querySuccess',
          payload: {
            list: data.datas.userList,
            total: data.datas.count,
            current: data.datas.pageNo
          }
        });
      }
    },
    *create({payload:params }, { call, put }){
      yield call(userservice.createUser,params);
      yield put({
        type: 'query',
        payload:{}
      })
    },
    *'delete'({payload:id }, { call, put }){
      
      yield call(userservice.deleteData,id);
      
      yield put({
        type: 'query',
        payload:{}
      })
    },
    *update({payload:params }, { call, put }){
      yield call(userservice.editUser,params);
      yield put({
        type: 'query',
        payload:{}
      })
    },
  },
  reducers: {
    showLoading(state){
      return { ...state, loading: true };
    }, // 控制加载状态的 reducer
    showModal(state,{payload}){
      console.log(payload);
      return {...state , ...payload,modalVisible:true }
    }, // 控制 Modal 显示状态的 reducer
    hideModal(state){
      return {...state , modalVisible: false , modalType:"create"}
    },
    // 使用服务器数据返回
    querySuccess(state, action){
      return {...state, ...action.payload, loading: false};
    },
    createSuccess(){},
    deleteSuccess(){},
    updateSuccess(){},
  }
}