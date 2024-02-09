import * as angular from "angular";
import { AppController } from "./controllers/app.controller";
import { ProductController } from "./controllers/app.productController";

angular.module("app", [])
    .controller("AppController", ['$http', AppController])
    .controller("ProductController", ['$http', '$location', ProductController]);
