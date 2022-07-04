
window.onload=function (){
    var vue=new Vue({
        "el":"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function (){
                alert("用户名不能为空");
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"cartInfo"
                    }
                })
                    .then(function (value){
                        vue.cart=value.data;
                    })
                    .catch(function (reason){

                    })
            },
            editCart2:function (cartItemId,byCount){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"editCart",
                        cartItemId:cartItemId,
                        byCount:byCount
                    }
                })
                    .then(function (value){
                        vue.getCart();
                    })
                    .catch(function (reason){

                    })
            },
            delCartItem:function (cartItemId){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"delCartItem",
                        id:cartItemId
                    }
                })
                    .then(function (value){
                        vue.getCart();
                    })
                    .catch(function (reason){

                    })
            }

        },
        mounted:function (){
            this.getCart();
        }
    });
}