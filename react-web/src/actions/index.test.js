import axios from 'axios';

import { ENDPOINT } from '../constants';
import { getData } from './';

describe('Action', () => {
  /** @test {Action#getData()} **/
  describe('#getData()', () => {

    it('makes a GET request to a defined endpoint', () => {
      spyOn(axios, 'get');
      getData();
      expect(axios.get).toHaveBeenCalledWith(ENDPOINT);
    });
    
  });
});