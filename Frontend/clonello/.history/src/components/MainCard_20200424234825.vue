<template>
        <div class="col-3 col-md-3 col-sm-3">
            <b-card id="duties-card" no-body header="Card with flush list group" <b-button variant="dark">Dark</b-button> >
                <b-list-group flush="">
                    <b-list-group-item href="#" class="flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">List group item heading</h5>
                        <small>3 days ago</small>
                        </div>

                        <p class="mb-1">
                        Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                        </p>

                        <small>Donec id elit non mi porta.</small>
                    </b-list-group-item>

                    <b-list-group-item href="#" class="flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">List group item heading</h5>
                        <small class="text-muted">3 days ago</small>
                        </div>

                        <p class="mb-1">
                        Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                        </p>

                        <small class="text-muted">Donec id elit non mi porta.</small>
                    </b-list-group-item>

                    <b-list-group-item href="#" disabled class="flex-column align-items-start">
                        <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1">Disabled List group item</h5>
                        <small class="text-muted">3 days ago</small>
                        </div>

                        <p class="mb-1">
                        Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.
                        </p>

                        <small class="text-muted">Donec id elit non mi porta.</small>
                    </b-list-group-item>
                </b-list-group>
            </b-card>
        </div>
</template>

<script>
export default {
    
}
</script>

<style>
#duties-card {
    background-color: lightgray;
}

</style>

