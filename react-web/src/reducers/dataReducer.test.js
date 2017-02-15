import { RECIEVED_DATA } from '../actions/actionTypes';
import dataReducer from './dataReducer';

describe('Reducer', () => {
  /** @test {dataReducer} */
  describe('dataReducer', () => {
    /** @test {dataReducer::RECIEVED_DATA} **/
    describe('dataReducer::RECIEVED_DATA', () => {
      
      it('displays a welcome message', () => {
        const store = dataReducer({}, {
          type: RECIEVED_DATA,
          payload: { data: 'testdata' }
        });

        expect(store).toEqual({ data: 'testdata' });
      });
      
    });
  });
});