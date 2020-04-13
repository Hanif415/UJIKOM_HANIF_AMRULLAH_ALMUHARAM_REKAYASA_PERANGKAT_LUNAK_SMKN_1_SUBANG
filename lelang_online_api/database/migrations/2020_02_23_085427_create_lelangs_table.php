<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateLelangsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('lelangs', function (Blueprint $table) {
            $table->bigIncrements('id_lelang');
            $table->bigInteger('id_barang')->unsigned()->index()->nullable();
            $table->date('tgl_lelang');
            $table->integer('harga_akhir')->nullable();
            $table->bigInteger('id_user')->unsigned()->index()->nullable();
            $table->bigInteger('id_petugas')->unsigned()->index()->nullable();
            $table->enum('status', ['dibuka', 'ditutup'])->nullable();
            $table->timestamps();

            $table->foreign('id_barang')->references('id_barang')->on('barangs')->onDelete('cascade');
            $table->foreign('id_user')->references('id_user')->on('masyarakats')->onDelete('cascade');
            $table->foreign('id_petugas')->references('id_petugas')->on('petugas')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('lelangs');
    }
}
