// ./src/components/Users/UserList.jsx
import React from 'react';

// 采用antd的UI组件
import { Table, Popconfirm ,Pagination } from 'antd';
import 'antd/dist/antd.css';

// 采用 stateless 的写法
const UserList = (props) => {
  const {
    total,
    loading,
    dataSource,
    doDelect,
    willEdit,
    pageChange,
    onShowSizeChange
  } = props;
  const columns = [{
    title: '姓名',
    dataIndex: 'name',
    key: 'name',
    render: (text) => <a href="#">{text}</a>,
  }, {
    title: '年龄',
    dataIndex: 'age',
    key: 'age',
  }, {
    title: '住址',
    dataIndex: 'address',
    key: 'address',
  }, {
    title: '操作',
    key: 'operation',
    render: (text, record) => (
      <p>
        <a onClick={e=>willEdit(record,e)}>编辑</a>
        &nbsp;
        <Popconfirm title="确定要删除吗？" onConfirm={doDelect.bind(null,record.id)}>
          <a>删除</a>
        </Popconfirm>
      </p>
    ),
  }];

  return (
    <div>
      <Table
        columns={columns}
        dataSource={dataSource}
        loading={loading}
        rowKey={record => record.id}
        pagination={false}
      />
      <Pagination showSizeChanger onShowSizeChange={onShowSizeChange} onChange={pageChange} defaultCurrent={10} total={total} />
    </div>
  );
}

export default UserList;