import React from 'react';
import { Router} from 'dva/router';
import PropTypes from 'prop-types';


// function RouterConfig({ history }) {
//   return (
//     <Router history={history}>
//       <Route path="/users" component={Users} />
//     </Router>
//   );
// }
const registerModel = (app, model) => {
  if(!(app._models.filter(m => m.namespace === model.namespace ).length === 1)){
    app.model(model);
  }
}

const RouterConfig = ({history,app}) => {
  const routes = [
    {
      path: '/',
      name: 'IndexPage',
      getComponent(nextState, cb){
        require.ensure([],(require) => {
          cb(null, require('./routes/IndexPage').default);
        });
      },
    },
    {
      path: '/users',
      name: 'Users',
      getComponent(nextState, cb){
        require.ensure([],(require) => {
          registerModel(app, require('./models/users').default);
          cb(null, require('./routes/Users').default);
        });
      },
    }
  ];
  return <Router history={history}  routes={routes}/>
}

RouterConfig.propTypes = {
  history: PropTypes.object,
  app: PropTypes.object
}

export default RouterConfig;
