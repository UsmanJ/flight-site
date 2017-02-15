import axios from 'axios';

import { ENDPOINT } from '../constants';
import { RECIEVED_DATA } from './actionTypes';

export const getData = () => {
  const fetchData = axios.get(ENDPOINT);

  return {
    type: RECIEVED_DATA,
    payload: fetchData
  };
};

export const someAction = () => {};
