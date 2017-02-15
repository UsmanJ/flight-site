import React from 'react';
import { render } from 'enzyme';

import ReduxPromise from 'redux-promise';
import { createStore, applyMiddleware, combineReducers } from 'redux';

import Hello from './Hello';
import reducers from '../reducers';

const combinedReducers = combineReducers(reducers);

const createStoreWithMiddleware = applyMiddleware(ReduxPromise)(createStore);

describe('Component', () => {
  /** @test {Hello} */
  describe('Hello', () => {
    /** @test {Hello#render()} **/
    describe('#render()', () => {

      it('displays a welcome message', () => {
        const welcome = render(
          <Hello appName="Hell" store={createStoreWithMiddleware(combinedReducers)} />
        );

        expect(welcome.find('h1').text()).toEqual('Welcome to Hell');
      });

    });
  });
});
