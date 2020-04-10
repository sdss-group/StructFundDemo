import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import index from '@/components/index'
import baseinfo from '@/components/fundinfo/baseinfo'
import fundLot from '@/components/fundLot/fundLot'
import accountTrans from '@/components/accountTrans/accountTrans'
import transTrans from '@/components/transTrans/transTrans'
import orgAmt from '@/components/orgAmt/orgAmt'
import saleBillDate from '@/components/saleBillDate/saleBillDate'
import fundSale from '@/components/fundinfoset/fundSale'
import costparameters from '@/components/fundinfo/costparameters'
import fundCustType from '@/components/fundCustType/fundCustType'
import workDay from '@/components/tradeDay/workDay'
import confirmDay from '@/components/tradeDay/confirmDay'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/baseinfo',
      name: 'baseinfo',
      component: baseinfo
    },
    {
      path: '/costparameters',
      name: 'costparameters',
      component: costparameters
    },
    {
      path: '/fundLot',
      name: 'fundLot',
      component: fundLot,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/accountTrans',
      name: 'accountTrans',
      component: accountTrans,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/transTrans',
      name: 'transTrans',
      component: transTrans,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/orgAmt',
      name: 'orgAmt',
      component: orgAmt,
      meta: {
        keepAlive: true
      }
    },
    {
      path: '/saleBillDate',
      name: 'saleBillDate',
      component: saleBillDate
    },
    {
      path: '/fundSale',
      name: 'fundSale',
      component: fundSale
    },
    {
      path: '/fundCustType',
      name: 'fundCustType',
      component: fundCustType
    },
    {
      path: '/workDay',
      name: 'workDay',
      component: workDay
    },
    {
      path: '/confirmDay',
      name: 'confirmDay',
      component: confirmDay
    }
  ]
})
