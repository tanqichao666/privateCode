import React from 'react';
import { Modal, Button,Input,Form } from 'antd'; 
const FormItem=Form.Item;
const UserModal = (props) =>{
    const {visible,item={},title,onSave,onEdit,onCancel,form:{
        getFieldDecorator,
        validateFields,
        getFieldValue,
    }} = props;
debugger
    const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 14 },
      }
    
    const handleOk = ()=>{
        validateFields((errors, values)=> {
          if (!!errors) {
            console.log('Errors in form!!!');
            return;
          }
            const {id} = values;
            if(id==undefined){
                onSave(values);
            }else {
                onEdit(values);
            }
        })
    }

    const validateNumber = (rule, value, callback) =>{
        let age = getFieldValue('age');
        let  objRegExp=/^[\d]+$/;
        if(!objRegExp.test(age)){
            callback("请输入数字!!!");
        }else if(0>=age||age>=150){
          callback("请输入正确年龄!!!");
        }else {
            callback();
        }
    }

    return (
        <Modal
            visible={visible}
            title={title}
            onOk={handleOk}
            width={800}
            onCancel={onCancel}
            footer={[
            <Button key="back" onClick={onCancel}>取消</Button>,
            <Button key="submit" type="primary" onClick={handleOk}>
                保存
            </Button>,
            ]}
        >
          <Form horizontal onSubmit={handleOk}>
          <FormItem>
              {
                getFieldDecorator('id', {
                  initialValue: item.id,
                })(<Input type ='hidden'/>)
              }
            </FormItem>
            <FormItem
              {...formItemLayout}
              label="姓名:"
            >
              {
                getFieldDecorator('name', {
                    rules: [{
                        
                      }],
                  initialValue: item.name,
                })(<Input />)
              }
            </FormItem>
            <FormItem
              {...formItemLayout}
              label="年龄:"
            >
              {
                getFieldDecorator('age', {
                    rules: [ {
                        validator: validateNumber,
                      }],
                  initialValue: item.age
                })(<Input />)
              }
            </FormItem>
            <FormItem
              {...formItemLayout}
              label="地址:"
            >
              {
                getFieldDecorator('address', {
                  initialValue: item.address,
                })(<Input />)
              }
            </FormItem>
          </Form>
        </Modal>
    );
}

export default Form.create()(UserModal);