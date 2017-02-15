import React from 'react';
import ReactDOM from 'react-dom';
import ReduxPromise from 'redux-promise';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import { Router, Route, Redirect, browserHistory } from 'react-router';
import { syncHistoryWithStore, routerReducer } from 'react-router-redux';

import Hello from './components/Hello';
import reducers from './reducers';

import './index.scss';

const storeEnhancer = compose(
  applyMiddleware(ReduxPromise),
  (
    (process.env.NODE_ENV !== 'production' && window.__REDUX_DEVTOOLS_EXTENSION__)
    ? window.__REDUX_DEVTOOLS_EXTENSION__()
    : noop => noop
  )
);

const store = createStore(
  combineReducers({ ...reducers, routing: routerReducer }),
  undefined,
  storeEnhancer
);

const history = syncHistoryWithStore(browserHistory, store);

// You can remove this once you've created your own root component
const Home = () => (
  <Hello appName={'Flight Hunt'} />
);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path="/" component={Home}>
        {/* Sub-routes here */}
        <Redirect from="*" to="/" />
      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
);
