import axios from 'axios'
import Config from '@/Config'
// 查询产品客户类型
function queryAllAgencyAndProcode () {
  return axios({
    url: 'http://' + Config.ip + ':' + Config.port + '/queryTrans/queryAllAgencyAndProcode ',
    method: 'get'
  })
}

export default {
  queryAllAgencyAndProcode
}
