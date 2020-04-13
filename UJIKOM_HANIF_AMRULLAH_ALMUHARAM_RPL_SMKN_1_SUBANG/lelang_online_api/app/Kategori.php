<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Kategori extends Model
{
    public function barang()
    {
        return $this->belongsToMany(Barang::class);
    }
}
