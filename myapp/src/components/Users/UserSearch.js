import React from 'react';
import styles from './UserSearch.less';
import {Button, Form, Input} from 'antd'
const FormItem = Form.Item;
const Search = Input.Search;


const UserSearch = (props) => {


const {filter,onSearch,onAdd,form: {
  getFieldDecorator,
  getFieldsValue,
  setFieldsValue
}} = props;
;

  const handleSearch = () => {
    let fields =  getFieldsValue();
    onSearch(fields);
  }
  const handleReset =() => {
    let fields =  getFieldsValue();
    for(let item of Object.keys(fields)){
      if({}.hasOwnProperty.call(fields,item)){
        fields[item]=undefined;
      }
    }
    setFieldsValue(fields);
  }
  const {name,age,address} = filter;


    return (
      <div className={styles.normal}>
      <div className={styles.search}>
          <Form layout="inline" onSubmit={handleSearch} >
              <FormItem>
                {getFieldDecorator('name', {initialValue:name})
                (
                  <Search
                  placeholder="请输入姓名"
                  onSearch={handleSearch}
                  style={{ width: 200 }}
                  id="name" />
                )}
              </FormItem>
              <FormItem>
                {getFieldDecorator('age', {initialValue:age})
                (
                    <Search
                      placeholder="请输入年龄"
                      onSearch={handleSearch}
                      style={{ width: 200 }}
                      id="age" />
                )}
              </FormItem>
              <FormItem>
              {getFieldDecorator('address', {
                    initialValue:address
                  })(
                    <Search
                      placeholder="请输入地址"
                      onSearch={handleSearch}
                      style={{ width: 200 }}
                      id="address" />
                )}
              </FormItem>
              <Button type="primary" htmlType="submit"  icon="search" className={styles.buttonStyle}>查询</Button>
              <Button type="primary"  icon="retweet" className={styles.buttonStyle} onClick={handleReset}>重置</Button>
          </Form>
      </div>
      <div>
          <div className={styles.create}><Button type="primary" icon="plus-circle-o" onClick={onAdd} className={styles.buttonStyle}>添加</Button></div>
      </div>
    </div>
      )
}
  
export default Form.create()(UserSearch);