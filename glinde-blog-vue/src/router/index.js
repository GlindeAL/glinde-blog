import Vue from 'vue'
import VueRouter from 'vue-router'
import Welcome from "@/views/Welcome";
import Home from "@/views/Home";
import Blogs from "@/views/Home/Blogs";
import Mailbox from "@/views/Home/Mailbox";
import SingleBlog from "@/views/Home/SingleBlog";
import Admin from "@/views/Admin";
import EditBlog from "@/views/Admin/EditBlog";
import AdminBlogs from "@/views/Admin/AdminBlogs";
import AdminMailbox from "@/views/Admin/AdminMailbox";

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/welcome"
  },
  {
    path: "/welcome",
    component: Welcome,
  },
  {
    path: "/home",
    component: Home,
    redirect: "/home/blogs",
    children: [
      { path: "/home/blogs", component: Blogs },
      { path: "/home/mailbox", component: Mailbox },
      { path: "/home/blog/:bid", component: SingleBlog }
    ]
  },
  {
    path: "/admin",
    component: Admin,
    redirect: "/admin/edit",
    meta: {
      requireAuth: true
    },
    children: [
      {
        path: "/admin/edit",
        component: EditBlog,
        meta: {
          requireAuth: true
        }
      },
      {
        path: "/admin/blogs",
        component: AdminBlogs,
        meta: {
          requireAuth: true
        }
      },
      {
        path: "/admin/mailbox",
        component: AdminMailbox,
        meta: {
          requireAuth: true
        }
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to,from,next) => {
  if(to.meta.requireAuth){
    const token = window.localStorage.getItem("token");
    if(!token){
      return next('/welcome');
    }
    Vue.prototype.checkAuth().catch(err => {
      if(err){
        return next('/welcome');
      }
    })
  }
  return next();
})

export default router
