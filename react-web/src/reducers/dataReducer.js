import { RECIEVED_DATA } from '../actions/actionTypes';

export default (state = {}, action) => {
  switch (action.type) {
    case RECIEVED_DATA:
      return {
        data: action.payload.data
      };

    default:
      return state;
  }
};
