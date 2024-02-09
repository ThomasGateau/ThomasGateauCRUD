import { IController } from 'angular';

export class AppController implements IController {

    public products: any[] = [];
    public lastId: number 
    public newProduct: any 
    

    static $inject = ['$http', '$location'];
    constructor(private $http: ng.IHttpService, private $location: ng.ILocationService) {
            this.getProducts()
    }

    getProducts(): void {
        this.$http.get<any[]>('http://localhost:8080/api/products')
            .then(response => {
                this.products = response.data;
                this.lastId = this.products.length > 0 ? this.products[this.products.length - 1].id + 1 : 1;
                this.newProduct = {
                    id: this.lastId
                };
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }

    createProduct(): void {
        this.$http.post('http://localhost:8080/api/products', this.newProduct)
            .then(response => {
                this.getProducts(); // Refresh products after adding
            })
            .catch(error => {
                console.error('Error adding product:', error);
            });
    }

    deleteProduct(product: any): void {
        this.$http.delete(`http://localhost:8080/api/products/${product.id}`)
            .then(response => {
                this.getProducts(); // Refresh products after deleting
            })
            .catch(error => {
                console.error('Error deleting product:', error);
            });
    }
}