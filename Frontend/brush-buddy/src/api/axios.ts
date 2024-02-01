import axios from 'axios';
import { VITE_SERVER_URL } from '../.env';

export const instance = axios.create({
    baseURL: VITE_SERVER_URL,
    withCredentials: true
  });