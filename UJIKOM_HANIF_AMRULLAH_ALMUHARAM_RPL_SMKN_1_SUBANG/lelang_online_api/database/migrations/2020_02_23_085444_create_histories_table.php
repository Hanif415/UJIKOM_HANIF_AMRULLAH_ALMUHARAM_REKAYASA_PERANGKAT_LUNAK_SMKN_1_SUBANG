<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateHistoriesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('histories', function (Blueprint $table) {
            $table->bigIncrements('id_history');
            $table->bigInteger('id_lelang')->unsigned()->index()->nullable();
            $table->bigInteger('id_barang')->unsigned()->index()->nullable();
            $table->bigInteger('id_user')->unsigned()->index()->nullable();
            $table->integer('penawaran_harga');
            $table->timestamps();

            $table->foreign('id_lelang')->references('id_lelang')->on('lelangs')->onDelete('cascade');
            $table->foreign('id_barang')->references('id_barang')->on('barangs')->onDelete('cascade');
            $table->foreign('id_user')->references('id_user')->on('masyarakats')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('histories');
    }
}
