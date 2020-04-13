<?php

namespace App;

use Illuminate\Database\Eloquent\Model;
use App\Lelang;
use App\History;

class Barang extends Model
{
    protected $guarded = ['id_barang'];
    protected $primaryKey = 'id_barang';

    public function lelang()
    {
        return $this->hasOne(Lelang::class);
    }

    public function history()
    {
        return $this->hasOne(History::class);
    }

    public function kategori()
    {
        return $this->hasOne(Kategori::class);
    }
}
